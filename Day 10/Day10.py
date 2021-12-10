import statistics

chars = []
with open('input.txt', 'r') as file:
    line = file.read().split('\n')
    chars.append(line)
stack = []
def partOne():
    sum=0
    for line in chars:
        stack = []
        for word in line:
            i=0
            while(i<len(word)):
                c=word[i]
                if((c=='{') | (c=='(') | (c=='[') | (c=='<')):
                   stack.append(c)
                else:
                    opening = stack[-1]
                    stack.pop()
                    if((c == '}') & (opening != '{')):
                        sum+=1197
                        i=len(word)+1
                        break
                    elif((c == ')') & (opening != '(')):
                        sum+=3
                        i=len(word)+1
                        break
                    elif((c == '>') & (opening != '<')):
                        sum+=25137
                        i=len(word)+1
                        break
                    elif((c == ']') & (opening != '[')):
                        sum+=57
                        i=len(word)+1
                        break
                i+=1
    print(sum)

def partTwo():
    sum=0
    scores = []
    for line in chars:
        for word in line:
            i=0
            stack = []
            while(i<len(word)):                
                c=word[i]
                if((c=='{') | (c=='(') | (c=='[') | (c=='<')):
                   stack.append(c)
                else:
                    opening = stack[-1]
                    stack.pop()
                    if((c == '}') & (opening != '{')):
                        i=len(word)+1
                        break
                    elif((c == ')') & (opening != '(')):
                        i=len(word)+1
                        break
                    elif((c == '>') & (opening != '<')):
                        i=len(word)+1
                        break
                    elif((c == ']') & (opening != '[')):
                        i=len(word)+1
                        break
                i+=1
                if(i==len(word)):
                    sum=0
                    print(word)
                    while(len(stack)>0):
                        opening = stack.pop()
                        print(opening)
                        sum*=5
                        if opening=='(':
                            sum+=1
                        elif opening=='[':
                            sum+=2
                        elif opening=='{':
                            sum+=3
                        elif opening=='<':
                            sum+=4
                    scores.append(sum)
    print(statistics.median(scores))

partOne()
partTwo()
